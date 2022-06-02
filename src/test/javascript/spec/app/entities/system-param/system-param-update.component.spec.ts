/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SystemParamUpdateComponent from '@/entities/system-param/system-param-update.vue';
import SystemParamClass from '@/entities/system-param/system-param-update.component';
import SystemParamService from '@/entities/system-param/system-param.service';

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
  describe('SystemParam Management Update Component', () => {
    let wrapper: Wrapper<SystemParamClass>;
    let comp: SystemParamClass;
    let systemParamServiceStub: SinonStubbedInstance<SystemParamService>;

    beforeEach(() => {
      systemParamServiceStub = sinon.createStubInstance<SystemParamService>(SystemParamService);

      wrapper = shallowMount<SystemParamClass>(SystemParamUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          systemParamService: () => systemParamServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.systemParam = entity;
        systemParamServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(systemParamServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.systemParam = entity;
        systemParamServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(systemParamServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSystemParam = { id: 123 };
        systemParamServiceStub.find.resolves(foundSystemParam);
        systemParamServiceStub.retrieve.resolves([foundSystemParam]);

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
