/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ExampleComponent from '@/entities/example/example.vue';
import ExampleClass from '@/entities/example/example.component';
import ExampleService from '@/entities/example/example.service';
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
  describe('Example Management Component', () => {
    let wrapper: Wrapper<ExampleClass>;
    let comp: ExampleClass;
    let exampleServiceStub: SinonStubbedInstance<ExampleService>;

    beforeEach(() => {
      exampleServiceStub = sinon.createStubInstance<ExampleService>(ExampleService);
      exampleServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ExampleClass>(ExampleComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          exampleService: () => exampleServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      exampleServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllExamples();
      await comp.$nextTick();

      // THEN
      expect(exampleServiceStub.retrieve.called).toBeTruthy();
      expect(comp.examples[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      exampleServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(exampleServiceStub.retrieve.callCount).toEqual(1);

      comp.removeExample();
      await comp.$nextTick();

      // THEN
      expect(exampleServiceStub.delete.called).toBeTruthy();
      expect(exampleServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
