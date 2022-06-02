/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SlowSqlLoggingUpdateComponent from '@/entities/slow-sql-logging/slow-sql-logging-update.vue';
import SlowSqlLoggingClass from '@/entities/slow-sql-logging/slow-sql-logging-update.component';
import SlowSqlLoggingService from '@/entities/slow-sql-logging/slow-sql-logging.service';

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
  describe('SlowSqlLogging Management Update Component', () => {
    let wrapper: Wrapper<SlowSqlLoggingClass>;
    let comp: SlowSqlLoggingClass;
    let slowSqlLoggingServiceStub: SinonStubbedInstance<SlowSqlLoggingService>;

    beforeEach(() => {
      slowSqlLoggingServiceStub = sinon.createStubInstance<SlowSqlLoggingService>(SlowSqlLoggingService);

      wrapper = shallowMount<SlowSqlLoggingClass>(SlowSqlLoggingUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          slowSqlLoggingService: () => slowSqlLoggingServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.slowSqlLogging = entity;
        slowSqlLoggingServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(slowSqlLoggingServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.slowSqlLogging = entity;
        slowSqlLoggingServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(slowSqlLoggingServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSlowSqlLogging = { id: 123 };
        slowSqlLoggingServiceStub.find.resolves(foundSlowSqlLogging);
        slowSqlLoggingServiceStub.retrieve.resolves([foundSlowSqlLogging]);

        // WHEN
        comp.beforeRouteEnter({ params: { slowSqlLoggingId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.slowSqlLogging).toBe(foundSlowSqlLogging);
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
