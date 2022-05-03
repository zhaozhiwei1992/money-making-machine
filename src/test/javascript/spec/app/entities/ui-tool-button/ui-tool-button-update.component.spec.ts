/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiToolButtonUpdateComponent from '@/entities/ui-tool-button/ui-tool-button-update.vue';
import UiToolButtonClass from '@/entities/ui-tool-button/ui-tool-button-update.component';
import UiToolButtonService from '@/entities/ui-tool-button/ui-tool-button.service';

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
  describe('UiToolButton Management Update Component', () => {
    let wrapper: Wrapper<UiToolButtonClass>;
    let comp: UiToolButtonClass;
    let uiToolButtonServiceStub: SinonStubbedInstance<UiToolButtonService>;

    beforeEach(() => {
      uiToolButtonServiceStub = sinon.createStubInstance<UiToolButtonService>(UiToolButtonService);

      wrapper = shallowMount<UiToolButtonClass>(UiToolButtonUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          uiToolButtonService: () => uiToolButtonServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.uiToolButton = entity;
        uiToolButtonServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiToolButtonServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.uiToolButton = entity;
        uiToolButtonServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiToolButtonServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiToolButton = { id: 123 };
        uiToolButtonServiceStub.find.resolves(foundUiToolButton);
        uiToolButtonServiceStub.retrieve.resolves([foundUiToolButton]);

        // WHEN
        comp.beforeRouteEnter({ params: { uiToolButtonId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiToolButton).toBe(foundUiToolButton);
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
