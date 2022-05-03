/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UiEditformDetailComponent from '@/entities/ui-editform/ui-editform-details.vue';
import UiEditformClass from '@/entities/ui-editform/ui-editform-details.component';
import UiEditformService from '@/entities/ui-editform/ui-editform.service';
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
  describe('UiEditform Management Detail Component', () => {
    let wrapper: Wrapper<UiEditformClass>;
    let comp: UiEditformClass;
    let uiEditformServiceStub: SinonStubbedInstance<UiEditformService>;

    beforeEach(() => {
      uiEditformServiceStub = sinon.createStubInstance<UiEditformService>(UiEditformService);

      wrapper = shallowMount<UiEditformClass>(UiEditformDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { uiEditformService: () => uiEditformServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUiEditform = { id: 123 };
        uiEditformServiceStub.find.resolves(foundUiEditform);

        // WHEN
        comp.retrieveUiEditform(123);
        await comp.$nextTick();

        // THEN
        expect(comp.uiEditform).toBe(foundUiEditform);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiEditform = { id: 123 };
        uiEditformServiceStub.find.resolves(foundUiEditform);

        // WHEN
        comp.beforeRouteEnter({ params: { uiEditformId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiEditform).toBe(foundUiEditform);
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
