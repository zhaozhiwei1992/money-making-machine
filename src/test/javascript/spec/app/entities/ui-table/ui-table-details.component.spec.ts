/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UiTableDetailComponent from '@/entities/ui-table/ui-table-details.vue';
import UiTableClass from '@/entities/ui-table/ui-table-details.component';
import UiTableService from '@/entities/ui-table/ui-table.service';
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
  describe('UiTable Management Detail Component', () => {
    let wrapper: Wrapper<UiTableClass>;
    let comp: UiTableClass;
    let uiTableServiceStub: SinonStubbedInstance<UiTableService>;

    beforeEach(() => {
      uiTableServiceStub = sinon.createStubInstance<UiTableService>(UiTableService);

      wrapper = shallowMount<UiTableClass>(UiTableDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { uiTableService: () => uiTableServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUiTable = { id: 123 };
        uiTableServiceStub.find.resolves(foundUiTable);

        // WHEN
        comp.retrieveUiTable(123);
        await comp.$nextTick();

        // THEN
        expect(comp.uiTable).toBe(foundUiTable);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiTable = { id: 123 };
        uiTableServiceStub.find.resolves(foundUiTable);

        // WHEN
        comp.beforeRouteEnter({ params: { uiTableId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiTable).toBe(foundUiTable);
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
