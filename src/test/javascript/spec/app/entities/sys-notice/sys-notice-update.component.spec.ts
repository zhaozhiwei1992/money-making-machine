/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysNoticeUpdateComponent from '@/entities/sys-notice/sys-notice-update.vue';
import SysNoticeClass from '@/entities/sys-notice/sys-notice-update.component';
import SysNoticeService from '@/entities/sys-notice/sys-notice.service';

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
  describe('SysNotice Management Update Component', () => {
    let wrapper: Wrapper<SysNoticeClass>;
    let comp: SysNoticeClass;
    let sysNoticeServiceStub: SinonStubbedInstance<SysNoticeService>;

    beforeEach(() => {
      sysNoticeServiceStub = sinon.createStubInstance<SysNoticeService>(SysNoticeService);

      wrapper = shallowMount<SysNoticeClass>(SysNoticeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          sysNoticeService: () => sysNoticeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.sysNotice = entity;
        sysNoticeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysNoticeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.sysNotice = entity;
        sysNoticeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysNoticeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSysNotice = { id: 123 };
        sysNoticeServiceStub.find.resolves(foundSysNotice);
        sysNoticeServiceStub.retrieve.resolves([foundSysNotice]);

        // WHEN
        comp.beforeRouteEnter({ params: { sysNoticeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.sysNotice).toBe(foundSysNotice);
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
