/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LeaveTypeDetailComponent from '@/entities/leave-type/leave-type-details.vue';
import LeaveTypeClass from '@/entities/leave-type/leave-type-details.component';
import LeaveTypeService from '@/entities/leave-type/leave-type.service';
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
  describe('LeaveType Management Detail Component', () => {
    let wrapper: Wrapper<LeaveTypeClass>;
    let comp: LeaveTypeClass;
    let leaveTypeServiceStub: SinonStubbedInstance<LeaveTypeService>;

    beforeEach(() => {
      leaveTypeServiceStub = sinon.createStubInstance<LeaveTypeService>(LeaveTypeService);

      wrapper = shallowMount<LeaveTypeClass>(LeaveTypeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { leaveTypeService: () => leaveTypeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLeaveType = { id: 123 };
        leaveTypeServiceStub.find.resolves(foundLeaveType);

        // WHEN
        comp.retrieveLeaveType(123);
        await comp.$nextTick();

        // THEN
        expect(comp.leaveType).toBe(foundLeaveType);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLeaveType = { id: 123 };
        leaveTypeServiceStub.find.resolves(foundLeaveType);

        // WHEN
        comp.beforeRouteEnter({ params: { leaveTypeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.leaveType).toBe(foundLeaveType);
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
