/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TaskParamUpdateComponent from '@/entities/task-param/task-param-update.vue';
import TaskParamClass from '@/entities/task-param/task-param-update.component';
import TaskParamService from '@/entities/task-param/task-param.service';

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
  describe('TaskParam Management Update Component', () => {
    let wrapper: Wrapper<TaskParamClass>;
    let comp: TaskParamClass;
    let taskParamServiceStub: SinonStubbedInstance<TaskParamService>;

    beforeEach(() => {
      taskParamServiceStub = sinon.createStubInstance<TaskParamService>(TaskParamService);

      wrapper = shallowMount<TaskParamClass>(TaskParamUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          taskParamService: () => taskParamServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.taskParam = entity;
        taskParamServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(taskParamServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.taskParam = entity;
        taskParamServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(taskParamServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTaskParam = { id: 123 };
        taskParamServiceStub.find.resolves(foundTaskParam);
        taskParamServiceStub.retrieve.resolves([foundTaskParam]);

        // WHEN
        comp.beforeRouteEnter({ params: { taskParamId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.taskParam).toBe(foundTaskParam);
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
