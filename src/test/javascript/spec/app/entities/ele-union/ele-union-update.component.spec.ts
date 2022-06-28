/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import EleUnionUpdateComponent from '@/entities/ele-union/ele-union-update.vue';
import EleUnionClass from '@/entities/ele-union/ele-union-update.component';
import EleUnionService from '@/entities/ele-union/ele-union.service';

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
  describe('EleUnion Management Update Component', () => {
    let wrapper: Wrapper<EleUnionClass>;
    let comp: EleUnionClass;
    let eleUnionServiceStub: SinonStubbedInstance<EleUnionService>;

    beforeEach(() => {
      eleUnionServiceStub = sinon.createStubInstance<EleUnionService>(EleUnionService);

      wrapper = shallowMount<EleUnionClass>(EleUnionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          eleUnionService: () => eleUnionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.eleUnion = entity;
        eleUnionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(eleUnionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.eleUnion = entity;
        eleUnionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(eleUnionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundEleUnion = { id: 123 };
        eleUnionServiceStub.find.resolves(foundEleUnion);
        eleUnionServiceStub.retrieve.resolves([foundEleUnion]);

        // WHEN
        comp.beforeRouteEnter({ params: { eleUnionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.eleUnion).toBe(foundEleUnion);
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
