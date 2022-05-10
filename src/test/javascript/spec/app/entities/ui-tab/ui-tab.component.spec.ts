/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiTabComponent from '@/entities/ui-tab/ui-tab.vue';
import UiTabClass from '@/entities/ui-tab/ui-tab.component';
import UiTabService from '@/entities/ui-tab/ui-tab.service';
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
  describe('UiTab Management Component', () => {
    let wrapper: Wrapper<UiTabClass>;
    let comp: UiTabClass;
    let uiTabServiceStub: SinonStubbedInstance<UiTabService>;

    beforeEach(() => {
      uiTabServiceStub = sinon.createStubInstance<UiTabService>(UiTabService);
      uiTabServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UiTabClass>(UiTabComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          uiTabService: () => uiTabServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      uiTabServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUiTabs();
      await comp.$nextTick();

      // THEN
      expect(uiTabServiceStub.retrieve.called).toBeTruthy();
      expect(comp.uiTabs[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      uiTabServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(uiTabServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUiTab();
      await comp.$nextTick();

      // THEN
      expect(uiTabServiceStub.delete.called).toBeTruthy();
      expect(uiTabServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
