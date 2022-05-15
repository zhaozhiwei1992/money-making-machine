/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DataPermissionDetailsUpdateComponent from '@/entities/data-permission-details/data-permission-details-update.vue';
import DataPermissionDetailsClass from '@/entities/data-permission-details/data-permission-details-update.component';
import DataPermissionDetailsService from '@/entities/data-permission-details/data-permission-details.service';

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
  describe('DataPermissionDetails Management Update Component', () => {
    let wrapper: Wrapper<DataPermissionDetailsClass>;
    let comp: DataPermissionDetailsClass;
    let dataPermissionDetailsServiceStub: SinonStubbedInstance<DataPermissionDetailsService>;

    beforeEach(() => {
      dataPermissionDetailsServiceStub = sinon.createStubInstance<DataPermissionDetailsService>(DataPermissionDetailsService);

      wrapper = shallowMount<DataPermissionDetailsClass>(DataPermissionDetailsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          dataPermissionDetailsService: () => dataPermissionDetailsServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.dataPermissionDetails = entity;
        dataPermissionDetailsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(dataPermissionDetailsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.dataPermissionDetails = entity;
        dataPermissionDetailsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(dataPermissionDetailsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDataPermissionDetails = { id: 123 };
        dataPermissionDetailsServiceStub.find.resolves(foundDataPermissionDetails);
        dataPermissionDetailsServiceStub.retrieve.resolves([foundDataPermissionDetails]);

        // WHEN
        comp.beforeRouteEnter({ params: { dataPermissionDetailsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.dataPermissionDetails).toBe(foundDataPermissionDetails);
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
