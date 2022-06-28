/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LeaveSlipDetailComponent from '@/entities/leave-slip/leave-slip-details.vue';
import LeaveSlipClass from '@/entities/leave-slip/leave-slip-details.component';
import LeaveSlipService from '@/entities/leave-slip/leave-slip.service';
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
  describe('LeaveSlip Management Detail Component', () => {
    let wrapper: Wrapper<LeaveSlipClass>;
    let comp: LeaveSlipClass;
    let leaveSlipServiceStub: SinonStubbedInstance<LeaveSlipService>;

    beforeEach(() => {
      leaveSlipServiceStub = sinon.createStubInstance<LeaveSlipService>(LeaveSlipService);

      wrapper = shallowMount<LeaveSlipClass>(LeaveSlipDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { leaveSlipService: () => leaveSlipServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLeaveSlip = { id: 123 };
        leaveSlipServiceStub.find.resolves(foundLeaveSlip);

        // WHEN
        comp.retrieveLeaveSlip(123);
        await comp.$nextTick();

        // THEN
        expect(comp.leaveSlip).toBe(foundLeaveSlip);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLeaveSlip = { id: 123 };
        leaveSlipServiceStub.find.resolves(foundLeaveSlip);

        // WHEN
        comp.beforeRouteEnter({ params: { leaveSlipId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.leaveSlip).toBe(foundLeaveSlip);
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
