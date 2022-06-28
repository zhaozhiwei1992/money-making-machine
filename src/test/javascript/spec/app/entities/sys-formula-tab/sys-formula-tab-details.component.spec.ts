/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import SysFormulaTabDetailComponent from '@/entities/sys-formula-tab/sys-formula-tab-details.vue';
import SysFormulaTabClass from '@/entities/sys-formula-tab/sys-formula-tab-details.component';
import SysFormulaTabService from '@/entities/sys-formula-tab/sys-formula-tab.service';
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
  describe('SysFormulaTab Management Detail Component', () => {
    let wrapper: Wrapper<SysFormulaTabClass>;
    let comp: SysFormulaTabClass;
    let sysFormulaTabServiceStub: SinonStubbedInstance<SysFormulaTabService>;

    beforeEach(() => {
      sysFormulaTabServiceStub = sinon.createStubInstance<SysFormulaTabService>(SysFormulaTabService);

      wrapper = shallowMount<SysFormulaTabClass>(SysFormulaTabDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { sysFormulaTabService: () => sysFormulaTabServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysFormulaTab = { id: 123 };
        sysFormulaTabServiceStub.find.resolves(foundSysFormulaTab);

        // WHEN
        comp.retrieveSysFormulaTab(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysFormulaTab).toBe(foundSysFormulaTab);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSysFormulaTab = { id: 123 };
        sysFormulaTabServiceStub.find.resolves(foundSysFormulaTab);

        // WHEN
        comp.beforeRouteEnter({ params: { sysFormulaTabId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.sysFormulaTab).toBe(foundSysFormulaTab);
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
