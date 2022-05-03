/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiTableComponent from '@/entities/ui-table/ui-table.vue';
import UiTableClass from '@/entities/ui-table/ui-table.component';
import UiTableService from '@/entities/ui-table/ui-table.service';
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
  describe('UiTable Management Component', () => {
    let wrapper: Wrapper<UiTableClass>;
    let comp: UiTableClass;
    let uiTableServiceStub: SinonStubbedInstance<UiTableService>;

    beforeEach(() => {
      uiTableServiceStub = sinon.createStubInstance<UiTableService>(UiTableService);
      uiTableServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UiTableClass>(UiTableComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          uiTableService: () => uiTableServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      uiTableServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUiTables();
      await comp.$nextTick();

      // THEN
      expect(uiTableServiceStub.retrieve.called).toBeTruthy();
      expect(comp.uiTables[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      uiTableServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(uiTableServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUiTable();
      await comp.$nextTick();

      // THEN
      expect(uiTableServiceStub.delete.called).toBeTruthy();
      expect(uiTableServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
