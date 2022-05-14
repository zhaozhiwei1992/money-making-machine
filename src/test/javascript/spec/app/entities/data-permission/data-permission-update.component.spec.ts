/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DataPermissionUpdateComponent from '@/entities/data-permission/data-permission-update.vue';
import DataPermissionClass from '@/entities/data-permission/data-permission-update.component';
import DataPermissionService from '@/entities/data-permission/data-permission.service';

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
  describe('DataPermission Management Update Component', () => {
    let wrapper: Wrapper<DataPermissionClass>;
    let comp: DataPermissionClass;
    let dataPermissionServiceStub: SinonStubbedInstance<DataPermissionService>;

    beforeEach(() => {
      dataPermissionServiceStub = sinon.createStubInstance<DataPermissionService>(DataPermissionService);

      wrapper = shallowMount<DataPermissionClass>(DataPermissionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          dataPermissionService: () => dataPermissionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.dataPermission = entity;
        dataPermissionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(dataPermissionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.dataPermission = entity;
        dataPermissionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(dataPermissionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDataPermission = { id: 123 };
        dataPermissionServiceStub.find.resolves(foundDataPermission);
        dataPermissionServiceStub.retrieve.resolves([foundDataPermission]);

        // WHEN
        comp.beforeRouteEnter({ params: { dataPermissionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.dataPermission).toBe(foundDataPermission);
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
