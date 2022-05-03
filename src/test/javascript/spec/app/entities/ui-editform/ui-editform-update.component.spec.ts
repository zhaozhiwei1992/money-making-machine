/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiEditformUpdateComponent from '@/entities/ui-editform/ui-editform-update.vue';
import UiEditformClass from '@/entities/ui-editform/ui-editform-update.component';
import UiEditformService from '@/entities/ui-editform/ui-editform.service';

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
  describe('UiEditform Management Update Component', () => {
    let wrapper: Wrapper<UiEditformClass>;
    let comp: UiEditformClass;
    let uiEditformServiceStub: SinonStubbedInstance<UiEditformService>;

    beforeEach(() => {
      uiEditformServiceStub = sinon.createStubInstance<UiEditformService>(UiEditformService);

      wrapper = shallowMount<UiEditformClass>(UiEditformUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          uiEditformService: () => uiEditformServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.uiEditform = entity;
        uiEditformServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiEditformServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.uiEditform = entity;
        uiEditformServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiEditformServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiEditform = { id: 123 };
        uiEditformServiceStub.find.resolves(foundUiEditform);
        uiEditformServiceStub.retrieve.resolves([foundUiEditform]);

        // WHEN
        comp.beforeRouteEnter({ params: { uiEditformId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiEditform).toBe(foundUiEditform);
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
