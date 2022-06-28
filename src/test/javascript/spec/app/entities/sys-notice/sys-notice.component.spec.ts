/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SysNoticeComponent from '@/entities/sys-notice/sys-notice.vue';
import SysNoticeClass from '@/entities/sys-notice/sys-notice.component';
import SysNoticeService from '@/entities/sys-notice/sys-notice.service';
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
  describe('SysNotice Management Component', () => {
    let wrapper: Wrapper<SysNoticeClass>;
    let comp: SysNoticeClass;
    let sysNoticeServiceStub: SinonStubbedInstance<SysNoticeService>;

    beforeEach(() => {
      sysNoticeServiceStub = sinon.createStubInstance<SysNoticeService>(SysNoticeService);
      sysNoticeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SysNoticeClass>(SysNoticeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          sysNoticeService: () => sysNoticeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      sysNoticeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSysNotices();
      await comp.$nextTick();

      // THEN
      expect(sysNoticeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.sysNotices[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      sysNoticeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(sysNoticeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeSysNotice();
      await comp.$nextTick();

      // THEN
      expect(sysNoticeServiceStub.delete.called).toBeTruthy();
      expect(sysNoticeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
