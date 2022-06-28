/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import SysCollectColService from '@/entities/sys-collect-col/sys-collect-col.service';
import { SysCollectCol } from '@/shared/model/sys-collect-col.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('SysCollectCol Service', () => {
    let service: SysCollectColService;
    let elemDefault;

    beforeEach(() => {
      service = new SysCollectColService();
      elemDefault = new SysCollectCol(123, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', false, false, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a SysCollectCol', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a SysCollectCol', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a SysCollectCol', async () => {
        const returnedFromService = Object.assign(
          {
            colCname: 'BBBBBB',
            colEname: 'BBBBBB',
            tabId: 1,
            orderNum: 1,
            source: 'BBBBBB',
            isEdit: true,
            requirement: true,
            type: 'BBBBBB',
            config: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a SysCollectCol', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a SysCollectCol', async () => {
        const patchObject = Object.assign(
          {
            colEname: 'BBBBBB',
            tabId: 1,
            source: 'BBBBBB',
            isEdit: true,
            requirement: true,
            type: 'BBBBBB',
            config: 'BBBBBB',
          },
          new SysCollectCol()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a SysCollectCol', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of SysCollectCol', async () => {
        const returnedFromService = Object.assign(
          {
            colCname: 'BBBBBB',
            colEname: 'BBBBBB',
            tabId: 1,
            orderNum: 1,
            source: 'BBBBBB',
            isEdit: true,
            requirement: true,
            type: 'BBBBBB',
            config: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of SysCollectCol', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a SysCollectCol', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a SysCollectCol', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
