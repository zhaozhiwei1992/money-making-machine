/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import LeaveSlipComponent from '@/entities/leave-slip/leave-slip.vue';
import LeaveSlipClass from '@/entities/leave-slip/leave-slip.component';
import LeaveSlipService from '@/entities/leave-slip/leave-slip.service';
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
  describe('LeaveSlip Management Component', () => {
    let wrapper: Wrapper<LeaveSlipClass>;
    let comp: LeaveSlipClass;
    let leaveSlipServiceStub: SinonStubbedInstance<LeaveSlipService>;

    beforeEach(() => {
      leaveSlipServiceStub = sinon.createStubInstance<LeaveSlipService>(LeaveSlipService);
      leaveSlipServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LeaveSlipClass>(LeaveSlipComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          leaveSlipService: () => leaveSlipServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      leaveSlipServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLeaveSlips();
      await comp.$nextTick();

      // THEN
      expect(leaveSlipServiceStub.retrieve.called).toBeTruthy();
      expect(comp.leaveSlips[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      leaveSlipServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(leaveSlipServiceStub.retrieve.callCount).toEqual(1);

      comp.removeLeaveSlip();
      await comp.$nextTick();

      // THEN
      expect(leaveSlipServiceStub.delete.called).toBeTruthy();
      expect(leaveSlipServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
