/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RoleMenuComponent from '@/entities/role-menu/role-menu.vue';
import RoleMenuClass from '@/entities/role-menu/role-menu.component';
import RoleMenuService from '@/entities/role-menu/role-menu.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('RoleMenu Management Component', () => {
    let wrapper: Wrapper<RoleMenuClass>;
    let comp: RoleMenuClass;
    let roleMenuServiceStub: SinonStubbedInstance<RoleMenuService>;

    beforeEach(() => {
      roleMenuServiceStub = sinon.createStubInstance<RoleMenuService>(RoleMenuService);
      roleMenuServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RoleMenuClass>(RoleMenuComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          roleMenuService: () => roleMenuServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      roleMenuServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRoleMenus();
      await comp.$nextTick();

      // THEN
      expect(roleMenuServiceStub.retrieve.called).toBeTruthy();
      expect(comp.roleMenus[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      roleMenuServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(roleMenuServiceStub.retrieve.callCount).toEqual(1);

      comp.removeRoleMenu();
      await comp.$nextTick();

      // THEN
      expect(roleMenuServiceStub.delete.called).toBeTruthy();
      expect(roleMenuServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
