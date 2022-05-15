/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DataPermissionsRelUpdateComponent from '@/entities/data-permissions-rel/data-permissions-rel-update.vue';
import DataPermissionsRelClass from '@/entities/data-permissions-rel/data-permissions-rel-update.component';
import DataPermissionsRelService from '@/entities/data-permissions-rel/data-permissions-rel.service';

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
  describe('DataPermissionsRel Management Update Component', () => {
    let wrapper: Wrapper<DataPermissionsRelClass>;
    let comp: DataPermissionsRelClass;
    let dataPermissionsRelServiceStub: SinonStubbedInstance<DataPermissionsRelService>;

    beforeEach(() => {
      dataPermissionsRelServiceStub = sinon.createStubInstance<DataPermissionsRelService>(DataPermissionsRelService);

      wrapper = shallowMount<DataPermissionsRelClass>(DataPermissionsRelUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          dataPermissionsRelService: () => dataPermissionsRelServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.dataPermissionsRel = entity;
        dataPermissionsRelServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(dataPermissionsRelServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.dataPermissionsRel = entity;
        dataPermissionsRelServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(dataPermissionsRelServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDataPermissionsRel = { id: 123 };
        dataPermissionsRelServiceStub.find.resolves(foundDataPermissionsRel);
        dataPermissionsRelServiceStub.retrieve.resolves([foundDataPermissionsRel]);

        // WHEN
        comp.beforeRouteEnter({ params: { dataPermissionsRelId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.dataPermissionsRel).toBe(foundDataPermissionsRel);
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
