/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UiComponentDetailComponent from '@/entities/ui-component/ui-component-details.vue';
import UiComponentClass from '@/entities/ui-component/ui-component-details.component';
import UiComponentService from '@/entities/ui-component/ui-component.service';
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
  describe('UiComponent Management Detail Component', () => {
    let wrapper: Wrapper<UiComponentClass>;
    let comp: UiComponentClass;
    let uiComponentServiceStub: SinonStubbedInstance<UiComponentService>;

    beforeEach(() => {
      uiComponentServiceStub = sinon.createStubInstance<UiComponentService>(UiComponentService);

      wrapper = shallowMount<UiComponentClass>(UiComponentDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { uiComponentService: () => uiComponentServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUiComponent = { id: 123 };
        uiComponentServiceStub.find.resolves(foundUiComponent);

        // WHEN
        comp.retrieveUiComponent(123);
        await comp.$nextTick();

        // THEN
        expect(comp.uiComponent).toBe(foundUiComponent);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiComponent = { id: 123 };
        uiComponentServiceStub.find.resolves(foundUiComponent);

        // WHEN
        comp.beforeRouteEnter({ params: { uiComponentId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiComponent).toBe(foundUiComponent);
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
