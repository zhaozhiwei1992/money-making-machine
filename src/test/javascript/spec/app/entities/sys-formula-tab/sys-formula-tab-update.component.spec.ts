/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysFormulaTabUpdateComponent from '@/entities/sys-formula-tab/sys-formula-tab-update.vue';
import SysFormulaTabClass from '@/entities/sys-formula-tab/sys-formula-tab-update.component';
import SysFormulaTabService from '@/entities/sys-formula-tab/sys-formula-tab.service';

import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('SysFormulaTab Management Update Component', () => {
    let wrapper: Wrapper<SysFormulaTabClass>;
    let comp: SysFormulaTabClass;
    let sysFormulaTabServiceStub: SinonStubbedInstance<SysFormulaTabService>;

    beforeEach(() => {
      sysFormulaTabServiceStub = sinon.createStubInstance<SysFormulaTabService>(SysFormulaTabService);

      wrapper = shallowMount<SysFormulaTabClass>(SysFormulaTabUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          sysFormulaTabService: () => sysFormulaTabServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.sysFormulaTab = entity;
        sysFormulaTabServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysFormulaTabServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.sysFormulaTab = entity;
        sysFormulaTabServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysFormulaTabServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSysFormulaTab = { id: 123 };
        sysFormulaTabServiceStub.find.resolves(foundSysFormulaTab);
        sysFormulaTabServiceStub.retrieve.resolves([foundSysFormulaTab]);

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
