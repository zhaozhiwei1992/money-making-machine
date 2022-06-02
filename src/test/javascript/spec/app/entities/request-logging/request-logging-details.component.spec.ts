/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RequestLoggingDetailComponent from '@/entities/request-logging/request-logging-details.vue';
import RequestLoggingClass from '@/entities/request-logging/request-logging-details.component';
import RequestLoggingService from '@/entities/request-logging/request-logging.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RequestLogging Management Detail Component', () => {
    let wrapper: Wrapper<RequestLoggingClass>;
    let comp: RequestLoggingClass;
    let requestLoggingServiceStub: SinonStubbedInstance<RequestLoggingService>;

    beforeEach(() => {
      requestLoggingServiceStub = sinon.createStubInstance<RequestLoggingService>(RequestLoggingService);

      wrapper = shallowMount<RequestLoggingClass>(RequestLoggingDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { requestLoggingService: () => requestLoggingServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRequestLogging = { id: 123 };
        requestLoggingServiceStub.find.resolves(foundRequestLogging);

        // WHEN
        comp.retrieveRequestLogging(123);
        await comp.$nextTick();

        // THEN
        expect(comp.requestLogging).toBe(foundRequestLogging);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRequestLogging = { id: 123 };
        requestLoggingServiceStub.find.resolves(foundRequestLogging);

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
