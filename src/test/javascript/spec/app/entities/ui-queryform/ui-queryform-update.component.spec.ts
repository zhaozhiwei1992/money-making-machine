/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UiQueryformUpdateComponent from '@/entities/ui-queryform/ui-queryform-update.vue';
import UiQueryformClass from '@/entities/ui-queryform/ui-queryform-update.component';
import UiQueryformService from '@/entities/ui-queryform/ui-queryform.service';

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
  describe('UiQueryform Management Update Component', () => {
    let wrapper: Wrapper<UiQueryformClass>;
    let comp: UiQueryformClass;
    let uiQueryformServiceStub: SinonStubbedInstance<UiQueryformService>;

    beforeEach(() => {
      uiQueryformServiceStub = sinon.createStubInstance<UiQueryformService>(UiQueryformService);

      wrapper = shallowMount<UiQueryformClass>(UiQueryformUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          uiQueryformService: () => uiQueryformServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.uiQueryform = entity;
        uiQueryformServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiQueryformServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.uiQueryform = entity;
        uiQueryformServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uiQueryformServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUiQueryform = { id: 123 };
        uiQueryformServiceStub.find.resolves(foundUiQueryform);
        uiQueryformServiceStub.retrieve.resolves([foundUiQueryform]);

        // WHEN
        comp.beforeRouteEnter({ params: { uiQueryformId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.uiQueryform).toBe(foundUiQueryform);
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
