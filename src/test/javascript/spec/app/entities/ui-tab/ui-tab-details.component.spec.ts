/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UiTabDetailComponent from '@/entities/ui-tab/ui-tab-details.vue';
import UiTabClass from '@/entities/ui-tab/ui-tab-details.component';
import UiTabService from '@/entities/ui-tab/ui-tab.service';
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
  describe('UiTab Management Detail Component', () => {
    let wrapper: Wrapper<UiTabClass>;
    let comp: UiTabClass;
    let uiTabServiceStub: SinonStubbedInstance<UiTabService>;

    beforeEach(() => {
      uiTabServiceStub = sinon.createStubInstance<UiTabService>(UiTabService);

      wrapper = shallowMount<UiTabClass>(UiTabDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { uiTabService: () => uiTabServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUiTab = { id: 123 };
        uiTabServiceStub.find.resolves(foundUiTab);

        // WHEN
        comp.retrieveUiTab(123);
        await comp.$nextTick();

        // THEN
        expect(comp.uiTab).toBe(foundUiTab);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiTab = { id: 123 };
        uiTabServiceStub.find.resolves(foundUiTab);

        // WHEN
        comp.beforeRouteEnter({ params: { uiTabId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiTab).toBe(foundUiTab);
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
