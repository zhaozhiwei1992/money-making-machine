/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import EleUnionComponent from '@/entities/ele-union/ele-union.vue';
import EleUnionClass from '@/entities/ele-union/ele-union.component';
import EleUnionService from '@/entities/ele-union/ele-union.service';
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
  describe('EleUnion Management Component', () => {
    let wrapper: Wrapper<EleUnionClass>;
    let comp: EleUnionClass;
    let eleUnionServiceStub: SinonStubbedInstance<EleUnionService>;

    beforeEach(() => {
      eleUnionServiceStub = sinon.createStubInstance<EleUnionService>(EleUnionService);
      eleUnionServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<EleUnionClass>(EleUnionComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          eleUnionService: () => eleUnionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      eleUnionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllEleUnions();
      await comp.$nextTick();

      // THEN
      expect(eleUnionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.eleUnions[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      eleUnionServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(eleUnionServiceStub.retrieve.callCount).toEqual(1);

      comp.removeEleUnion();
      await comp.$nextTick();

      // THEN
      expect(eleUnionServiceStub.delete.called).toBeTruthy();
      expect(eleUnionServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
