/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiTabUpdateComponent from '@/entities/ui-tab/ui-tab-update.vue';
import UiTabClass from '@/entities/ui-tab/ui-tab-update.component';
import UiTabService from '@/entities/ui-tab/ui-tab.service';

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
  describe('UiTab Management Update Component', () => {
    let wrapper: Wrapper<UiTabClass>;
    let comp: UiTabClass;
    let uiTabServiceStub: SinonStubbedInstance<UiTabService>;

    beforeEach(() => {
      uiTabServiceStub = sinon.createStubInstance<UiTabService>(UiTabService);

      wrapper = shallowMount<UiTabClass>(UiTabUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          uiTabService: () => uiTabServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.uiTab = entity;
        uiTabServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiTabServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.uiTab = entity;
        uiTabServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiTabServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiTab = { id: 123 };
        uiTabServiceStub.find.resolves(foundUiTab);
        uiTabServiceStub.retrieve.resolves([foundUiTab]);

        // WHEN
        comp.beforeRouteEnter({ params: { uiTabId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiTab).toBe(foundUiTab);
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
