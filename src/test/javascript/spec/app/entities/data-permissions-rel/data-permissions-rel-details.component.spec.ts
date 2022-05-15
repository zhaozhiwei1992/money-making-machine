/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import DataPermissionsRelDetailComponent from '@/entities/data-permissions-rel/data-permissions-rel-details.vue';
import DataPermissionsRelClass from '@/entities/data-permissions-rel/data-permissions-rel-details.component';
import DataPermissionsRelService from '@/entities/data-permissions-rel/data-permissions-rel.service';
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
  describe('DataPermissionsRel Management Detail Component', () => {
    let wrapper: Wrapper<DataPermissionsRelClass>;
    let comp: DataPermissionsRelClass;
    let dataPermissionsRelServiceStub: SinonStubbedInstance<DataPermissionsRelService>;

    beforeEach(() => {
      dataPermissionsRelServiceStub = sinon.createStubInstance<DataPermissionsRelService>(DataPermissionsRelService);

      wrapper = shallowMount<DataPermissionsRelClass>(DataPermissionsRelDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { dataPermissionsRelService: () => dataPermissionsRelServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDataPermissionsRel = { id: 123 };
        dataPermissionsRelServiceStub.find.resolves(foundDataPermissionsRel);

        // WHEN
        comp.retrieveDataPermissionsRel(123);
        await comp.$nextTick();

        // THEN
        expect(comp.dataPermissionsRel).toBe(foundDataPermissionsRel);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDataPermissionsRel = { id: 123 };
        dataPermissionsRelServiceStub.find.resolves(foundDataPermissionsRel);

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
