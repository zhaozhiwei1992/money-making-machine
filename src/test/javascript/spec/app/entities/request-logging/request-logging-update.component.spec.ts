/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RequestLoggingUpdateComponent from '@/entities/request-logging/request-logging-update.vue';
import RequestLoggingClass from '@/entities/request-logging/request-logging-update.component';
import RequestLoggingService from '@/entities/request-logging/request-logging.service';

import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('RequestLogging Management Update Component', () => {
    let wrapper: Wrapper<RequestLoggingClass>;
    let comp: RequestLoggingClass;
    let requestLoggingServiceStub: SinonStubbedInstance<RequestLoggingService>;

    beforeEach(() => {
      requestLoggingServiceStub = sinon.createStubInstance<RequestLoggingService>(RequestLoggingService);

      wrapper = shallowMount<RequestLoggingClass>(RequestLoggingUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          requestLoggingService: () => requestLoggingServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.requestLogging = entity;
        requestLoggingServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(requestLoggingServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.requestLogging = entity;
        requestLoggingServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(requestLoggingServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRequestLogging = { id: 123 };
        requestLoggingServiceStub.find.resolves(foundRequestLogging);
        requestLoggingServiceStub.retrieve.resolves([foundRequestLogging]);

        // WHEN
        comp.beforeRouteEnter({ params: { requestLoggingId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.requestLogging).toBe(foundRequestLogging);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
