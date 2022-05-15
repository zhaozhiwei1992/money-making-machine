/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RoleMenuUpdateComponent from '@/entities/role-menu/role-menu-update.vue';
import RoleMenuClass from '@/entities/role-menu/role-menu-update.component';
import RoleMenuService from '@/entities/role-menu/role-menu.service';

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
  describe('RoleMenu Management Update Component', () => {
    let wrapper: Wrapper<RoleMenuClass>;
    let comp: RoleMenuClass;
    let roleMenuServiceStub: SinonStubbedInstance<RoleMenuService>;

    beforeEach(() => {
      roleMenuServiceStub = sinon.createStubInstance<RoleMenuService>(RoleMenuService);

      wrapper = shallowMount<RoleMenuClass>(RoleMenuUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          roleMenuService: () => roleMenuServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.roleMenu = entity;
        roleMenuServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(roleMenuServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.roleMenu = entity;
        roleMenuServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(roleMenuServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRoleMenu = { id: 123 };
        roleMenuServiceStub.find.resolves(foundRoleMenu);
        roleMenuServiceStub.retrieve.resolves([foundRoleMenu]);

        // WHEN
        comp.beforeRouteEnter({ params: { roleMenuId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.roleMenu).toBe(foundRoleMenu);
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
