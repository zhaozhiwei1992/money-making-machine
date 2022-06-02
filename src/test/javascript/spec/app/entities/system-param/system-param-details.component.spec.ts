/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import SystemParamDetailComponent from '@/entities/system-param/system-param-details.vue';
import SystemParamClass from '@/entities/system-param/system-param-details.component';
import SystemParamService from '@/entities/system-param/system-param.service';
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
  describe('SystemParam Management Detail Component', () => {
    let wrapper: Wrapper<SystemParamClass>;
    let comp: SystemParamClass;
    let systemParamServiceStub: SinonStubbedInstance<SystemParamService>;

    beforeEach(() => {
      systemParamServiceStub = sinon.createStubInstance<SystemParamService>(SystemParamService);

      wrapper = shallowMount<SystemParamClass>(SystemParamDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { systemParamService: () => systemParamServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSystemParam = { id: 123 };
        systemParamServiceStub.find.resolves(foundSystemParam);

        // WHEN
        comp.retrieveSystemParam(123);
        await comp.$nextTick();

        // THEN
        expect(comp.systemParam).toBe(foundSystemParam);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSystemParam = { id: 123 };
        systemParamServiceStub.find.resolves(foundSystemParam);

        // WHEN
        comp.beforeRouteEnter({ params: { systemParamId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.systemParam).toBe(foundSystemParam);
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
