/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RoleMenuToolButtonComponent from '@/entities/role-menu-tool-button/role-menu-tool-button.vue';
import RoleMenuToolButtonClass from '@/entities/role-menu-tool-button/role-menu-tool-button.component';
import RoleMenuToolButtonService from '@/entities/role-menu-tool-button/role-menu-tool-button.service';
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
  describe('RoleMenuToolButton Management Component', () => {
    let wrapper: Wrapper<RoleMenuToolButtonClass>;
    let comp: RoleMenuToolButtonClass;
    let roleMenuToolButtonServiceStub: SinonStubbedInstance<RoleMenuToolButtonService>;

    beforeEach(() => {
      roleMenuToolButtonServiceStub = sinon.createStubInstance<RoleMenuToolButtonService>(RoleMenuToolButtonService);
      roleMenuToolButtonServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RoleMenuToolButtonClass>(RoleMenuToolButtonComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          roleMenuToolButtonService: () => roleMenuToolButtonServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      roleMenuToolButtonServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRoleMenuToolButtons();
      await comp.$nextTick();

      // THEN
      expect(roleMenuToolButtonServiceStub.retrieve.called).toBeTruthy();
      expect(comp.roleMenuToolButtons[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      roleMenuToolButtonServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(roleMenuToolButtonServiceStub.retrieve.callCount).toEqual(1);

      comp.removeRoleMenuToolButton();
      await comp.$nextTick();

      // THEN
      expect(roleMenuToolButtonServiceStub.delete.called).toBeTruthy();
      expect(roleMenuToolButtonServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
