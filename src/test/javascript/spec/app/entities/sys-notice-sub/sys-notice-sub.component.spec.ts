/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysNoticeSubComponent from '@/entities/sys-notice-sub/sys-notice-sub.vue';
import SysNoticeSubClass from '@/entities/sys-notice-sub/sys-notice-sub.component';
import SysNoticeSubService from '@/entities/sys-notice-sub/sys-notice-sub.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('SysNoticeSub Management Component', () => {
    let wrapper: Wrapper<SysNoticeSubClass>;
    let comp: SysNoticeSubClass;
    let sysNoticeSubServiceStub: SinonStubbedInstance<SysNoticeSubService>;

    beforeEach(() => {
      sysNoticeSubServiceStub = sinon.createStubInstance<SysNoticeSubService>(SysNoticeSubService);
      sysNoticeSubServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SysNoticeSubClass>(SysNoticeSubComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          sysNoticeSubService: () => sysNoticeSubServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      sysNoticeSubServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSysNoticeSubs();
      await comp.$nextTick();

      // THEN
      expect(sysNoticeSubServiceStub.retrieve.called).toBeTruthy();
      expect(comp.sysNoticeSubs[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      sysNoticeSubServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(sysNoticeSubServiceStub.retrieve.callCount).toEqual(1);

      comp.removeSysNoticeSub();
      await comp.$nextTick();

      // THEN
      expect(sysNoticeSubServiceStub.delete.called).toBeTruthy();
      expect(sysNoticeSubServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
