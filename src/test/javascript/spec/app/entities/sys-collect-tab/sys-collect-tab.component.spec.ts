/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysCollectTabComponent from '@/entities/sys-collect-tab/sys-collect-tab.vue';
import SysCollectTabClass from '@/entities/sys-collect-tab/sys-collect-tab.component';
import SysCollectTabService from '@/entities/sys-collect-tab/sys-collect-tab.service';
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
  describe('SysCollectTab Management Component', () => {
    let wrapper: Wrapper<SysCollectTabClass>;
    let comp: SysCollectTabClass;
    let sysCollectTabServiceStub: SinonStubbedInstance<SysCollectTabService>;

    beforeEach(() => {
      sysCollectTabServiceStub = sinon.createStubInstance<SysCollectTabService>(SysCollectTabService);
      sysCollectTabServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SysCollectTabClass>(SysCollectTabComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          sysCollectTabService: () => sysCollectTabServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      sysCollectTabServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSysCollectTabs();
      await comp.$nextTick();

      // THEN
      expect(sysCollectTabServiceStub.retrieve.called).toBeTruthy();
      expect(comp.sysCollectTabs[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      sysCollectTabServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(sysCollectTabServiceStub.retrieve.callCount).toEqual(1);

      comp.removeSysCollectTab();
      await comp.$nextTick();

      // THEN
      expect(sysCollectTabServiceStub.delete.called).toBeTruthy();
      expect(sysCollectTabServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
