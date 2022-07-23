/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import SysNoticeSubDetailComponent from '@/entities/sys-notice-sub/sys-notice-sub-details.vue';
import SysNoticeSubClass from '@/entities/sys-notice-sub/sys-notice-sub-details.component';
import SysNoticeSubService from '@/entities/sys-notice-sub/sys-notice-sub.service';
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
  describe('SysNoticeSub Management Detail Component', () => {
    let wrapper: Wrapper<SysNoticeSubClass>;
    let comp: SysNoticeSubClass;
    let sysNoticeSubServiceStub: SinonStubbedInstance<SysNoticeSubService>;

    beforeEach(() => {
      sysNoticeSubServiceStub = sinon.createStubInstance<SysNoticeSubService>(SysNoticeSubService);

      wrapper = shallowMount<SysNoticeSubClass>(SysNoticeSubDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { sysNoticeSubService: () => sysNoticeSubServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysNoticeSub = { id: 123 };
        sysNoticeSubServiceStub.find.resolves(foundSysNoticeSub);

        // WHEN
        comp.retrieveSysNoticeSub(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysNoticeSub).toBe(foundSysNoticeSub);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSysNoticeSub = { id: 123 };
        sysNoticeSubServiceStub.find.resolves(foundSysNoticeSub);

        // WHEN
        comp.beforeRouteEnter({ params: { sysNoticeSubId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.sysNoticeSub).toBe(foundSysNoticeSub);
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
