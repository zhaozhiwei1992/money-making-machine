/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RoleMenuToolButtonDetailComponent from '@/entities/role-menu-tool-button/role-menu-tool-button-details.vue';
import RoleMenuToolButtonClass from '@/entities/role-menu-tool-button/role-menu-tool-button-details.component';
import RoleMenuToolButtonService from '@/entities/role-menu-tool-button/role-menu-tool-button.service';
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
  describe('RoleMenuToolButton Management Detail Component', () => {
    let wrapper: Wrapper<RoleMenuToolButtonClass>;
    let comp: RoleMenuToolButtonClass;
    let roleMenuToolButtonServiceStub: SinonStubbedInstance<RoleMenuToolButtonService>;

    beforeEach(() => {
      roleMenuToolButtonServiceStub = sinon.createStubInstance<RoleMenuToolButtonService>(RoleMenuToolButtonService);

      wrapper = shallowMount<RoleMenuToolButtonClass>(RoleMenuToolButtonDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { roleMenuToolButtonService: () => roleMenuToolButtonServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRoleMenuToolButton = { id: 123 };
        roleMenuToolButtonServiceStub.find.resolves(foundRoleMenuToolButton);

        // WHEN
        comp.retrieveRoleMenuToolButton(123);
        await comp.$nextTick();

        // THEN
        expect(comp.roleMenuToolButton).toBe(foundRoleMenuToolButton);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRoleMenuToolButton = { id: 123 };
        roleMenuToolButtonServiceStub.find.resolves(foundRoleMenuToolButton);

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
