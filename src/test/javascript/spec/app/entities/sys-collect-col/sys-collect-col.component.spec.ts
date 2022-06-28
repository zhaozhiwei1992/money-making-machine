/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysCollectColComponent from '@/entities/sys-collect-col/sys-collect-col.vue';
import SysCollectColClass from '@/entities/sys-collect-col/sys-collect-col.component';
import SysCollectColService from '@/entities/sys-collect-col/sys-collect-col.service';
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
  describe('SysCollectCol Management Component', () => {
    let wrapper: Wrapper<SysCollectColClass>;
    let comp: SysCollectColClass;
    let sysCollectColServiceStub: SinonStubbedInstance<SysCollectColService>;

    beforeEach(() => {
      sysCollectColServiceStub = sinon.createStubInstance<SysCollectColService>(SysCollectColService);
      sysCollectColServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SysCollectColClass>(SysCollectColComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          sysCollectColService: () => sysCollectColServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      sysCollectColServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSysCollectCols();
      await comp.$nextTick();

      // THEN
      expect(sysCollectColServiceStub.retrieve.called).toBeTruthy();
      expect(comp.sysCollectCols[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      sysCollectColServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(sysCollectColServiceStub.retrieve.callCount).toEqual(1);

      comp.removeSysCollectCol();
      await comp.$nextTick();

      // THEN
      expect(sysCollectColServiceStub.delete.called).toBeTruthy();
      expect(sysCollectColServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
