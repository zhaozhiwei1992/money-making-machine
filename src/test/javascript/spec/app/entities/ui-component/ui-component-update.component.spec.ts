/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiComponentUpdateComponent from '@/entities/ui-component/ui-component-update.vue';
import UiComponentClass from '@/entities/ui-component/ui-component-update.component';
import UiComponentService from '@/entities/ui-component/ui-component.service';

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
  describe('UiComponent Management Update Component', () => {
    let wrapper: Wrapper<UiComponentClass>;
    let comp: UiComponentClass;
    let uiComponentServiceStub: SinonStubbedInstance<UiComponentService>;

    beforeEach(() => {
      uiComponentServiceStub = sinon.createStubInstance<UiComponentService>(UiComponentService);

      wrapper = shallowMount<UiComponentClass>(UiComponentUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          uiComponentService: () => uiComponentServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.uiComponent = entity;
        uiComponentServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiComponentServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.uiComponent = entity;
        uiComponentServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiComponentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiComponent = { id: 123 };
        uiComponentServiceStub.find.resolves(foundUiComponent);
        uiComponentServiceStub.retrieve.resolves([foundUiComponent]);

        // WHEN
        comp.beforeRouteEnter({ params: { uiComponentId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiComponent).toBe(foundUiComponent);
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
