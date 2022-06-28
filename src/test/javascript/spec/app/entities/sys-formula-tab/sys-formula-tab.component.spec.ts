/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysFormulaTabComponent from '@/entities/sys-formula-tab/sys-formula-tab.vue';
import SysFormulaTabClass from '@/entities/sys-formula-tab/sys-formula-tab.component';
import SysFormulaTabService from '@/entities/sys-formula-tab/sys-formula-tab.service';
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
  describe('SysFormulaTab Management Component', () => {
    let wrapper: Wrapper<SysFormulaTabClass>;
    let comp: SysFormulaTabClass;
    let sysFormulaTabServiceStub: SinonStubbedInstance<SysFormulaTabService>;

    beforeEach(() => {
      sysFormulaTabServiceStub = sinon.createStubInstance<SysFormulaTabService>(SysFormulaTabService);
      sysFormulaTabServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SysFormulaTabClass>(SysFormulaTabComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          sysFormulaTabService: () => sysFormulaTabServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      sysFormulaTabServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSysFormulaTabs();
      await comp.$nextTick();

      // THEN
      expect(sysFormulaTabServiceStub.retrieve.called).toBeTruthy();
      expect(comp.sysFormulaTabs[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      sysFormulaTabServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(sysFormulaTabServiceStub.retrieve.callCount).toEqual(1);

      comp.removeSysFormulaTab();
      await comp.$nextTick();

      // THEN
      expect(sysFormulaTabServiceStub.delete.called).toBeTruthy();
      expect(sysFormulaTabServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
