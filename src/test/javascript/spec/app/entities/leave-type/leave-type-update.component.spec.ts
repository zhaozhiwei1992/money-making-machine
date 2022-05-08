/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import LeaveTypeUpdateComponent from '@/entities/leave-type/leave-type-update.vue';
import LeaveTypeClass from '@/entities/leave-type/leave-type-update.component';
import LeaveTypeService from '@/entities/leave-type/leave-type.service';

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
  describe('LeaveType Management Update Component', () => {
    let wrapper: Wrapper<LeaveTypeClass>;
    let comp: LeaveTypeClass;
    let leaveTypeServiceStub: SinonStubbedInstance<LeaveTypeService>;

    beforeEach(() => {
      leaveTypeServiceStub = sinon.createStubInstance<LeaveTypeService>(LeaveTypeService);

      wrapper = shallowMount<LeaveTypeClass>(LeaveTypeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          leaveTypeService: () => leaveTypeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.leaveType = entity;
        leaveTypeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(leaveTypeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.leaveType = entity;
        leaveTypeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(leaveTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLeaveType = { id: 123 };
        leaveTypeServiceStub.find.resolves(foundLeaveType);
        leaveTypeServiceStub.retrieve.resolves([foundLeaveType]);

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
