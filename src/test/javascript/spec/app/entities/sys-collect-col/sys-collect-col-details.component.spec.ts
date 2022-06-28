/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import SysCollectColDetailComponent from '@/entities/sys-collect-col/sys-collect-col-details.vue';
import SysCollectColClass from '@/entities/sys-collect-col/sys-collect-col-details.component';
import SysCollectColService from '@/entities/sys-collect-col/sys-collect-col.service';
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
  describe('SysCollectCol Management Detail Component', () => {
    let wrapper: Wrapper<SysCollectColClass>;
    let comp: SysCollectColClass;
    let sysCollectColServiceStub: SinonStubbedInstance<SysCollectColService>;

    beforeEach(() => {
      sysCollectColServiceStub = sinon.createStubInstance<SysCollectColService>(SysCollectColService);

      wrapper = shallowMount<SysCollectColClass>(SysCollectColDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { sysCollectColService: () => sysCollectColServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysCollectCol = { id: 123 };
        sysCollectColServiceStub.find.resolves(foundSysCollectCol);

        // WHEN
        comp.retrieveSysCollectCol(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysCollectCol).toBe(foundSysCollectCol);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSysCollectCol = { id: 123 };
        sysCollectColServiceStub.find.resolves(foundSysCollectCol);

        // WHEN
        comp.beforeRouteEnter({ params: { sysCollectColId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.sysCollectCol).toBe(foundSysCollectCol);
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
