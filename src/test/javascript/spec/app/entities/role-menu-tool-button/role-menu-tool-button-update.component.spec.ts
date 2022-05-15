/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RoleMenuToolButtonUpdateComponent from '@/entities/role-menu-tool-button/role-menu-tool-button-update.vue';
import RoleMenuToolButtonClass from '@/entities/role-menu-tool-button/role-menu-tool-button-update.component';
import RoleMenuToolButtonService from '@/entities/role-menu-tool-button/role-menu-tool-button.service';

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
  describe('RoleMenuToolButton Management Update Component', () => {
    let wrapper: Wrapper<RoleMenuToolButtonClass>;
    let comp: RoleMenuToolButtonClass;
    let roleMenuToolButtonServiceStub: SinonStubbedInstance<RoleMenuToolButtonService>;

    beforeEach(() => {
      roleMenuToolButtonServiceStub = sinon.createStubInstance<RoleMenuToolButtonService>(RoleMenuToolButtonService);

      wrapper = shallowMount<RoleMenuToolButtonClass>(RoleMenuToolButtonUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          roleMenuToolButtonService: () => roleMenuToolButtonServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.roleMenuToolButton = entity;
        roleMenuToolButtonServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(roleMenuToolButtonServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.roleMenuToolButton = entity;
        roleMenuToolButtonServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(roleMenuToolButtonServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRoleMenuToolButton = { id: 123 };
        roleMenuToolButtonServiceStub.find.resolves(foundRoleMenuToolButton);
        roleMenuToolButtonServiceStub.retrieve.resolves([foundRoleMenuToolButton]);

        // WHEN
        comp.beforeRouteEnter({ params: { roleMenuToolButtonId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.roleMenuToolButton).toBe(foundRoleMenuToolButton);
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
