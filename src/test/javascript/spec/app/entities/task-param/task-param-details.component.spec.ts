/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TaskParamDetailComponent from '@/entities/task-param/task-param-details.vue';
import TaskParamClass from '@/entities/task-param/task-param-details.component';
import TaskParamService from '@/entities/task-param/task-param.service';
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
  describe('TaskParam Management Detail Component', () => {
    let wrapper: Wrapper<TaskParamClass>;
    let comp: TaskParamClass;
    let taskParamServiceStub: SinonStubbedInstance<TaskParamService>;

    beforeEach(() => {
      taskParamServiceStub = sinon.createStubInstance<TaskParamService>(TaskParamService);

      wrapper = shallowMount<TaskParamClass>(TaskParamDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { taskParamService: () => taskParamServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTaskParam = { id: 123 };
        taskParamServiceStub.find.resolves(foundTaskParam);

        // WHEN
        comp.retrieveTaskParam(123);
        await comp.$nextTick();

        // THEN
        expect(comp.taskParam).toBe(foundTaskParam);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTaskParam = { id: 123 };
        taskParamServiceStub.find.resolves(foundTaskParam);

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
