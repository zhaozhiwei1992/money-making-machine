/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import LeaveTypeComponent from '@/entities/leave-type/leave-type.vue';
import LeaveTypeClass from '@/entities/leave-type/leave-type.component';
import LeaveTypeService from '@/entities/leave-type/leave-type.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('LeaveType Management Component', () => {
    let wrapper: Wrapper<LeaveTypeClass>;
    let comp: LeaveTypeClass;
    let leaveTypeServiceStub: SinonStubbedInstance<LeaveTypeService>;

    beforeEach(() => {
      leaveTypeServiceStub = sinon.createStubInstance<LeaveTypeService>(LeaveTypeService);
      leaveTypeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LeaveTypeClass>(LeaveTypeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          leaveTypeService: () => leaveTypeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      leaveTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLeaveTypes();
      await comp.$nextTick();

      // THEN
      expect(leaveTypeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.leaveTypes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      leaveTypeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(leaveTypeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeLeaveType();
      await comp.$nextTick();

      // THEN
      expect(leaveTypeServiceStub.delete.called).toBeTruthy();
      expect(leaveTypeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
