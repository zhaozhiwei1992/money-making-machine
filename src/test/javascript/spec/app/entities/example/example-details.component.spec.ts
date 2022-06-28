/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ExampleDetailComponent from '@/entities/example/example-details.vue';
import ExampleClass from '@/entities/example/example-details.component';
import ExampleService from '@/entities/example/example.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Example Management Detail Component', () => {
    let wrapper: Wrapper<ExampleClass>;
    let comp: ExampleClass;
    let exampleServiceStub: SinonStubbedInstance<ExampleService>;

    beforeEach(() => {
      exampleServiceStub = sinon.createStubInstance<ExampleService>(ExampleService);

      wrapper = shallowMount<ExampleClass>(ExampleDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { exampleService: () => exampleServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundExample = { id: 123 };
        exampleServiceStub.find.resolves(foundExample);

        // WHEN
        comp.retrieveExample(123);
        await comp.$nextTick();

        // THEN
        expect(comp.example).toBe(foundExample);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundExample = { id: 123 };
        exampleServiceStub.find.resolves(foundExample);

        // WHEN
        comp.beforeRouteEnter({ params: { exampleId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.example).toBe(foundExample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
