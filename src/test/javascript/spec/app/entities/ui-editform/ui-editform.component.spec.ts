/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiEditformComponent from '@/entities/ui-editform/ui-editform.vue';
import UiEditformClass from '@/entities/ui-editform/ui-editform.component';
import UiEditformService from '@/entities/ui-editform/ui-editform.service';
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
  describe('UiEditform Management Component', () => {
    let wrapper: Wrapper<UiEditformClass>;
    let comp: UiEditformClass;
    let uiEditformServiceStub: SinonStubbedInstance<UiEditformService>;

    beforeEach(() => {
      uiEditformServiceStub = sinon.createStubInstance<UiEditformService>(UiEditformService);
      uiEditformServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UiEditformClass>(UiEditformComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          uiEditformService: () => uiEditformServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      uiEditformServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUiEditforms();
      await comp.$nextTick();

      // THEN
      expect(uiEditformServiceStub.retrieve.called).toBeTruthy();
      expect(comp.uiEditforms[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      uiEditformServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(uiEditformServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUiEditform();
      await comp.$nextTick();

      // THEN
      expect(uiEditformServiceStub.delete.called).toBeTruthy();
      expect(uiEditformServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
