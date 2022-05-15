/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RoleMenuDetailComponent from '@/entities/role-menu/role-menu-details.vue';
import RoleMenuClass from '@/entities/role-menu/role-menu-details.component';
import RoleMenuService from '@/entities/role-menu/role-menu.service';
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
  describe('RoleMenu Management Detail Component', () => {
    let wrapper: Wrapper<RoleMenuClass>;
    let comp: RoleMenuClass;
    let roleMenuServiceStub: SinonStubbedInstance<RoleMenuService>;

    beforeEach(() => {
      roleMenuServiceStub = sinon.createStubInstance<RoleMenuService>(RoleMenuService);

      wrapper = shallowMount<RoleMenuClass>(RoleMenuDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { roleMenuService: () => roleMenuServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRoleMenu = { id: 123 };
        roleMenuServiceStub.find.resolves(foundRoleMenu);

        // WHEN
        comp.retrieveRoleMenu(123);
        await comp.$nextTick();

        // THEN
        expect(comp.roleMenu).toBe(foundRoleMenu);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRoleMenu = { id: 123 };
        roleMenuServiceStub.find.resolves(foundRoleMenu);

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
