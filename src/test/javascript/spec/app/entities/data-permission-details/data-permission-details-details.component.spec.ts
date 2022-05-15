/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import DataPermissionDetailsDetailComponent from '@/entities/data-permission-details/data-permission-details-details.vue';
import DataPermissionDetailsClass from '@/entities/data-permission-details/data-permission-details-details.component';
import DataPermissionDetailsService from '@/entities/data-permission-details/data-permission-details.service';
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
  describe('DataPermissionDetails Management Detail Component', () => {
    let wrapper: Wrapper<DataPermissionDetailsClass>;
    let comp: DataPermissionDetailsClass;
    let dataPermissionDetailsServiceStub: SinonStubbedInstance<DataPermissionDetailsService>;

    beforeEach(() => {
      dataPermissionDetailsServiceStub = sinon.createStubInstance<DataPermissionDetailsService>(DataPermissionDetailsService);

      wrapper = shallowMount<DataPermissionDetailsClass>(DataPermissionDetailsDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { dataPermissionDetailsService: () => dataPermissionDetailsServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDataPermissionDetails = { id: 123 };
        dataPermissionDetailsServiceStub.find.resolves(foundDataPermissionDetails);

        // WHEN
        comp.retrieveDataPermissionDetails(123);
        await comp.$nextTick();

        // THEN
        expect(comp.dataPermissionDetails).toBe(foundDataPermissionDetails);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDataPermissionDetails = { id: 123 };
        dataPermissionDetailsServiceStub.find.resolves(foundDataPermissionDetails);

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
