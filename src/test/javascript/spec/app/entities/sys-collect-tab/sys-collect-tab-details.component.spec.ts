/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import SysCollectTabDetailComponent from '@/entities/sys-collect-tab/sys-collect-tab-details.vue';
import SysCollectTabClass from '@/entities/sys-collect-tab/sys-collect-tab-details.component';
import SysCollectTabService from '@/entities/sys-collect-tab/sys-collect-tab.service';
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
  describe('SysCollectTab Management Detail Component', () => {
    let wrapper: Wrapper<SysCollectTabClass>;
    let comp: SysCollectTabClass;
    let sysCollectTabServiceStub: SinonStubbedInstance<SysCollectTabService>;

    beforeEach(() => {
      sysCollectTabServiceStub = sinon.createStubInstance<SysCollectTabService>(SysCollectTabService);

      wrapper = shallowMount<SysCollectTabClass>(SysCollectTabDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { sysCollectTabService: () => sysCollectTabServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysCollectTab = { id: 123 };
        sysCollectTabServiceStub.find.resolves(foundSysCollectTab);

        // WHEN
        comp.retrieveSysCollectTab(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysCollectTab).toBe(foundSysCollectTab);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSysCollectTab = { id: 123 };
        sysCollectTabServiceStub.find.resolves(foundSysCollectTab);

        // WHEN
        comp.beforeRouteEnter({ params: { sysCollectTabId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.sysCollectTab).toBe(foundSysCollectTab);
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
