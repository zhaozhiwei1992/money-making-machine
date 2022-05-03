/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiToolButtonComponent from '@/entities/ui-tool-button/ui-tool-button.vue';
import UiToolButtonClass from '@/entities/ui-tool-button/ui-tool-button.component';
import UiToolButtonService from '@/entities/ui-tool-button/ui-tool-button.service';
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
  describe('UiToolButton Management Component', () => {
    let wrapper: Wrapper<UiToolButtonClass>;
    let comp: UiToolButtonClass;
    let uiToolButtonServiceStub: SinonStubbedInstance<UiToolButtonService>;

    beforeEach(() => {
      uiToolButtonServiceStub = sinon.createStubInstance<UiToolButtonService>(UiToolButtonService);
      uiToolButtonServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UiToolButtonClass>(UiToolButtonComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          uiToolButtonService: () => uiToolButtonServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      uiToolButtonServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUiToolButtons();
      await comp.$nextTick();

      // THEN
      expect(uiToolButtonServiceStub.retrieve.called).toBeTruthy();
      expect(comp.uiToolButtons[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      uiToolButtonServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(uiToolButtonServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUiToolButton();
      await comp.$nextTick();

      // THEN
      expect(uiToolButtonServiceStub.delete.called).toBeTruthy();
      expect(uiToolButtonServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
