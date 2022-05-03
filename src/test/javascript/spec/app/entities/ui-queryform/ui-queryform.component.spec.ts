/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiQueryformComponent from '@/entities/ui-queryform/ui-queryform.vue';
import UiQueryformClass from '@/entities/ui-queryform/ui-queryform.component';
import UiQueryformService from '@/entities/ui-queryform/ui-queryform.service';
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
  describe('UiQueryform Management Component', () => {
    let wrapper: Wrapper<UiQueryformClass>;
    let comp: UiQueryformClass;
    let uiQueryformServiceStub: SinonStubbedInstance<UiQueryformService>;

    beforeEach(() => {
      uiQueryformServiceStub = sinon.createStubInstance<UiQueryformService>(UiQueryformService);
      uiQueryformServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UiQueryformClass>(UiQueryformComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          uiQueryformService: () => uiQueryformServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      uiQueryformServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUiQueryforms();
      await comp.$nextTick();

      // THEN
      expect(uiQueryformServiceStub.retrieve.called).toBeTruthy();
      expect(comp.uiQueryforms[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      uiQueryformServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(uiQueryformServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUiQueryform();
      await comp.$nextTick();

      // THEN
      expect(uiQueryformServiceStub.delete.called).toBeTruthy();
      expect(uiQueryformServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
