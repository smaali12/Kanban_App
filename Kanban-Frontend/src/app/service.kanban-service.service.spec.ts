import { TestBed } from '@angular/core/testing';

import { ServiceKanbanServiceService } from './service.kanban-service.service';

describe('ServiceKanbanServiceService', () => {
  let service: ServiceKanbanServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceKanbanServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
