/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiComponentComponent from '@/entities/ui-component/ui-component.vue';
import UiComponentClass from '@/entities/ui-component/ui-component.component';
import UiComponentService from '@/entities/ui-component/ui-component.service';
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
  describe('UiComponent Management Component', () => {
    let wrapper: Wrapper<UiComponentClass>;
    let comp: UiComponentClass;
    let uiComponentServiceStub: SinonStubbedInstance<UiComponentService>;

    beforeEach(() => {
      uiComponentServiceStub = sinon.createStubInstance<UiComponentService>(UiComponentService);
      uiComponentServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UiComponentClass>(UiComponentComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          uiComponentService: () => uiComponentServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      uiComponentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUiComponents();
      await comp.$nextTick();

      // THEN
      expect(uiComponentServiceStub.retrieve.called).toBeTruthy();
      expect(comp.uiComponents[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      uiComponentServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(uiComponentServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUiComponent();
      await comp.$nextTick();

      // THEN
      expect(uiComponentServiceStub.delete.called).toBeTruthy();
      expect(uiComponentServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
