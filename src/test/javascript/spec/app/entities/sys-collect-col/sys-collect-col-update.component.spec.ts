/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysCollectColUpdateComponent from '@/entities/sys-collect-col/sys-collect-col-update.vue';
import SysCollectColClass from '@/entities/sys-collect-col/sys-collect-col-update.component';
import SysCollectColService from '@/entities/sys-collect-col/sys-collect-col.service';

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
  describe('SysCollectCol Management Update Component', () => {
    let wrapper: Wrapper<SysCollectColClass>;
    let comp: SysCollectColClass;
    let sysCollectColServiceStub: SinonStubbedInstance<SysCollectColService>;

    beforeEach(() => {
      sysCollectColServiceStub = sinon.createStubInstance<SysCollectColService>(SysCollectColService);

      wrapper = shallowMount<SysCollectColClass>(SysCollectColUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          sysCollectColService: () => sysCollectColServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.sysCollectCol = entity;
        sysCollectColServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysCollectColServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.sysCollectCol = entity;
        sysCollectColServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysCollectColServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSysCollectCol = { id: 123 };
        sysCollectColServiceStub.find.resolves(foundSysCollectCol);
        sysCollectColServiceStub.retrieve.resolves([foundSysCollectCol]);

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
