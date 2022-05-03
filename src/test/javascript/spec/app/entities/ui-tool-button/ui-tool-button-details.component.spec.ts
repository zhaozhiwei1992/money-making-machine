/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UiToolButtonDetailComponent from '@/entities/ui-tool-button/ui-tool-button-details.vue';
import UiToolButtonClass from '@/entities/ui-tool-button/ui-tool-button-details.component';
import UiToolButtonService from '@/entities/ui-tool-button/ui-tool-button.service';
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
  describe('UiToolButton Management Detail Component', () => {
    let wrapper: Wrapper<UiToolButtonClass>;
    let comp: UiToolButtonClass;
    let uiToolButtonServiceStub: SinonStubbedInstance<UiToolButtonService>;

    beforeEach(() => {
      uiToolButtonServiceStub = sinon.createStubInstance<UiToolButtonService>(UiToolButtonService);

      wrapper = shallowMount<UiToolButtonClass>(UiToolButtonDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { uiToolButtonService: () => uiToolButtonServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUiToolButton = { id: 123 };
        uiToolButtonServiceStub.find.resolves(foundUiToolButton);

        // WHEN
        comp.retrieveUiToolButton(123);
        await comp.$nextTick();

        // THEN
        expect(comp.uiToolButton).toBe(foundUiToolButton);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiToolButton = { id: 123 };
        uiToolButtonServiceStub.find.resolves(foundUiToolButton);

        // WHEN
        comp.beforeRouteEnter({ params: { uiToolButtonId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiToolButton).toBe(foundUiToolButton);
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
